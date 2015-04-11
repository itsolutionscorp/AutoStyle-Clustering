class School
  attr_accessor :db

  def initialize
    @db ||= {}
  end

  def add(name, grade)
    if db[grade]
      @db[grade] << name
    else
      @db[grade] = [name] 
    end
  end

  def grade(grade)
    if db[grade]
      db[grade]
    else
      []
    end
  end

  def sort
    hash = Hash[db.sort]
    hash[4].reverse!
    hash
  end 


end
