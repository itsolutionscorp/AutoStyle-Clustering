class School
  def initialize
    @students = {}
  end

  def add name, g
    ((@students[g] ||= []) << name).sort!
    self
  end

  def grade g
    @students[g] || []
  end

  def to_hash
    Hash[@students.sort]
  end
end
