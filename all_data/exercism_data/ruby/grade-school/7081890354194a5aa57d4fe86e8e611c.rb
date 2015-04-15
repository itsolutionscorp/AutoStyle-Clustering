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
    Hash[grades.inject({}) {|mem,v|
      mem[v[0]] = v[1].sort
      mem
    }.sort]
  end

  private
  def grades
    @grades ||= {}
  end
end
