class School

  def initialize
    @students = Hash.new []
  end

  def add name, grade
    students[ grade ] += [ name ]
    students[ grade ].sort!

    self
  end

  def grade grade
    students[ grade ].dup
  end

  def to_hash
    Hash[ students.sort ]
  end

private

  attr_reader :students

end
