class School
  attr_reader :db
  def initialize
    @db = Hash.new()
  end

  def add(student, grade)
    (@db[grade] ||= []) << student
  end

  def grade(which)
    @db[which] || []
  end

  def sort
    sorted_grades = @db.sort.each do |grades|
      students = grades[1]
      students.sort!
    end
    Hash[sorted_grades]
  end
end
