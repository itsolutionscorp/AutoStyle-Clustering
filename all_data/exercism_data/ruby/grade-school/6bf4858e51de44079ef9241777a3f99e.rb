class School
  attr_reader :archive

  def initialize
    @archive ||= Hash.new([])
  end

  def to_hash
    Hash[archive.sort]
  end

  def grade(level)
    archive[level]
  end

  def add(name, grade)
    students = archive.fetch(grade) do |grade|
      archive[grade] = []
    end

    students = students << name

    archive[grade] = students.sort
  end
end
