class School
  def initialize
    @db = {}
  end

  def db
    @db.dup
  end

  def add(name, grade)
    (@db[grade] ||= []) << name
  end

  def grade(grade)
    db.fetch(grade, [])
  end

  def sort
    db.to_a.sort.reduce({}) { |hsh, (grade, names)|
      hsh.merge(grade => names.sort)
    }
  end
end
