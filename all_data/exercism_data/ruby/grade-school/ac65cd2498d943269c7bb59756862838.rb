class School
  def db
    @db ||= {}
  end

  def add( name, grade )
    names = db.fetch(grade, [])
    names << name
    db[grade] = names
  end

  def grade(which_grade)
    db.fetch(which_grade, [])
  end

  def sort
    sh = {}
    db.keys.sort.each do |k|
      sh[k] = db[k].sort
    end
    sh
  end
end
