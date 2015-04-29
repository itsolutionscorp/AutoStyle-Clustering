class School

  def initialize
    @grades = Hash.new {|h, k| h[k] = [] }
  end

  def db
    @grades
  end

  def add(student, grade)
    @grades[grade] << student
    self
  end

  def grade(value)
    @grades[value].dup
  end

  def sort
    Hash[@grades.map(&name_sorter).sort]
  end

private

  def name_sorter
    proc {|grade, names| [grade, names.sort] }
  end
end
