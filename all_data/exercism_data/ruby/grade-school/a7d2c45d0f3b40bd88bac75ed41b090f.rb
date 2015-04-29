class School
  def initialize
    @students = {}
  end

  def add name, grade
    ((@students[grade] ||= []) << name).sort!
    self
  end

  def grade grade
    @students[grade] || []
  end

  def to_hash
    Hash[@students.sort]
  end
end
