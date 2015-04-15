class School

  attr_accessor :db

  def initialize
    @db_private = Hash.new {|hash, key| hash[key] = []}
    @db = safe_database
  end
  
  def safe_database
    copy(@db_private)
  end

  def copy(database)
    database.each_with_object(Hash.new([])) do |(grade,pupils), database_copy|
       database_copy[grade] = pupils.dup 
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
    Hash[@db.sort].each {|grade, pupils| pupils.sort! }
  end

end
