class School

  def initialize
    @database = Hash.new(Array.new())
  end

  def to_hash
    Hash[@database.map { |grade, students| [ grade, students.sort ] }.sort]
  end

  def add(name, grade)
    @database[grade] += [name]
  end

  def grade(grade_to_find)
    @database[grade_to_find].sort
  end
end
