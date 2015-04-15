class School
  def add(student, grade)
    (grades[grade] ||= []) << student
  end

  def db
    grades
  end

  def grade(num)
    grades[num] ||= []
  end

  def sort
    Hash[grades.each_with_object({}) {|grade, mem| mem[grade[0]] = grade[1].sort}.sort]
  end

  private
  def grades
    @grades ||= {}
  end
end
