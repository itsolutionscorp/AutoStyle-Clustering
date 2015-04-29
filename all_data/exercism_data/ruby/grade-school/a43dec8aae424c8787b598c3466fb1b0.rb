class School

  def initialize
    @student_registry = Hash.new
  end

  def to_hash
    Hash[@student_registry.sort]
  end

  def grade g
    @student_registry[g] || []
  end

  def add student, grade
    @student_registry[grade] ||= []
    @student_registry[grade] << student
    @student_registry[grade].sort!
  end
end
