class School

  def initialize
    @db = Hash.new { |h, k| h[k] = [] }
  end

  def add(name, grade)
    @db[grade.to_i] << name.to_s
  end

  def grade(grade)
    db[grade]
  end

  def sort
    sorted_array = db.sort.map { |grade, names| [grade, names.sort] }
    Hash[sorted_array]
  end

  def db
    @db.dup
  end

end
