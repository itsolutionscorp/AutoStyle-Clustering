class School
  def initialize
    @students = Hash.new([])
  end

  def add(student, grade)
    @students[grade] = @students[grade].dup
                                       .push(student)
                                       .sort
  end

  def grade(n)
    @students[n]
  end

  def to_hash
    @students.keys
             .sort
             .each_with_object({}) { |k, hash| hash[k] = @students[k] }
  end
end
