class School

  def initialize
    @db = Hash.new([])
  end

  attr_reader :db

  def add(student, grade)
    db[grade] += [student]
  end

  def grade(grade)
    db[grade]
  end

  def sort
    db.keys.sort.each_with_object({}) do |grade, sorted_db|
      sorted_db[grade] = db[grade].sort
    end
  end

end
