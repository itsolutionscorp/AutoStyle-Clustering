class School
  attr_reader :db

  def db
    @db ||= {}
  end

  def add(name, grade)
    roster = db.fetch(grade, [])
    roster << name
    db[grade] = roster
  end

  def grade(grade)
    db.fetch(grade, [])
  end

  def sort
    @db = db.keys.sort.each_with_object({}) do |key, sorted_db|
      sorted_db[key] = db[key].sort 
    end
  end

  def sort_grade(grade, sorted_db)
    sorted_db[grade] = db[grade].sort
  end

end
