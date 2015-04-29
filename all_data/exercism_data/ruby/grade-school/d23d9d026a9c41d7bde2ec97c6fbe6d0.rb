class School
  def initialize
  end

  def db
    @db ||= Hash.new { |hash, key| hash[key] = [] }
  end

  def add(student, grade)
    db[grade] << student
  end

  def grade(grade)
    db[grade]
  end

  def sort
    Hash[db.sort.map { |k,v| [k, v.sort] }]
  end
end
