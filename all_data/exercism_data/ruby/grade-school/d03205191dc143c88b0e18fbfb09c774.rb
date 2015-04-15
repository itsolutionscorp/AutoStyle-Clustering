class School

  attr_accessor :db

  def initialize
    @db_private = new_empty_database
    @db = safe_database
  end
  
  def new_empty_database
    Hash.new {|hash, key| hash[key] = []}
  end
  
  def safe_database
    copy(@db_private)
  end

  def copy(database)
    new_empty_db = 
    database.each_with_object(new_empty_database) do |(grade,students), database_copy|
       database_copy[grade] = students.dup 
    end
  end

  def add(student, grade)
    @db_private[grade] = @db_private[grade].push(student)
    @db = safe_database
  end

  def grade(number)
    @db = safe_database
    @db[number]
  end

  def sort
    @db = safe_database
    Hash[@db.sort].each {|grade, students| students.sort! }
  end

end
