public class Singleton {

    // Volatile - All thread gets updated value of _instance variable
	private volatile static Singleton _instance = null;
	
	// Private constructor - to prevent the object creation from outside the class
	private Singleton(){
   
        // prevent the object creation via Reflection
		if(_instance != null){
			throw new InstantiationError("More than one object creation is not allowed.");
		}
    
	}

	// Thread safe method to create instance
	public static Singleton getInstance(){
		if(_instance == null)                                // Single Checked
			synchronized(Singleton.class){
				if(_instance == null)                           // Double checked
					_instance = new Singleton();
			}

		return _instance;
	}
	
	// Avoid Cloning
	@Override
	public Object clone() throws CloneNotSupportedException{
		throw new CloneNotSupportedException();
	}
	
	// Avoid new object creation via Serialization - De-serialization
	protected Object readResolve() {
        return _instance;
    }

}
