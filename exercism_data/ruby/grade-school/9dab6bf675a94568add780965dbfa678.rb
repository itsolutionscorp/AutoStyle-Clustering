class School

  def initialize
    @db = Hash.new { [] }
    def @db.grades; keys; end
  end

  def db
    @db.dup
  end

  def add(name, grade)
    @db[grade] <<= name
  end

  def grade(grade)
    @db[grade]
  end

  def sort
    @db.grades.sort.inject({}) do |sorted, grade|
      sorted[grade] = @db[grade].sort
      sorted
    end
  end
end
