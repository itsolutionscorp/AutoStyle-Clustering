class School
  def initialize
    @repo = GradeRepo.new
  end

  def add(student, grade_number)
    grade = repo.find_or_create(grade_number)
    grade.add_student(student)
  end

  def grade(grade_number)
    repo.find_or_create(grade_number).students
  end

  def db
    repo.to_hash
  end

  def sort
    repo.sort.to_hash
  end

  private

  def repo
    @repo
  end
end

class GradeRepo
  def initialize(grades=nil)
    @grades = grades || []
  end

  def find_or_create(number)
    grade = grades.find {|g| g.level == number}
    if grade.nil?
      grade = Grade.new(number)
      grades << grade
    end

    grade
  end

  def sort
    GradeRepo.new(sorted_cloned_grades)
  end

  def sort!
    @grades = grades.sort.map {|g| g.sort! }
  end

  def to_hash
    hash = {}
    grades.each do |grade|
      hash.merge!(grade.to_hash) unless grade.empty?
    end
    hash
  end

  private

  def grades
    @grades
  end

  def sorted_cloned_grades
    sorted_grades = Marshal.load( Marshal.dump( grades.sort ) )
    sorted_grades.map do |g|
      g.sort
    end
  end
end

class Grade
  include Comparable

  attr_reader :level, :students

  def initialize(level, students=nil)
    @level = level
    @students = students || []
  end

  def add_student(student)
    students << student
  end

  def empty?
    students.empty?
  end

  def sort
    Grade.new(level, students.sort)
  end

  def sort!
    @students = students.sort
    self
  end

  def <=>(other_grade)
    level <=> other_grade.level
  end

  def to_hash
    hash = {}
    hash[level] = students
    hash
  end
end
