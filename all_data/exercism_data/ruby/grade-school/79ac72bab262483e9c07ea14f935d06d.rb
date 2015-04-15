class School
  attr_reader :db

  def initialize
    @db = Hash.new([])
  end

  def add(student, grade)
    if db.has_key?(grade)
      db[grade] << student
    else
      db[grade] = [student]
    end
  end

  def grade(n)
    db[n]
  end

  def sort
    ordered_grades.reduce({}) do |sorted, grade|
      sorted[grade] = grade(grade).sort
      sorted
    end
  end

  private

  attr_reader :database

  def ordered_grades
    db.keys.sort
  end
end
