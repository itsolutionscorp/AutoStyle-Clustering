class School

  def initialize
    @db = {}
  end

  def db
    @db.dup
  end

  def add(name, grade)
    unless @db[grade]
      @db[grade] = []
    end

    @db[grade] << name
  end

  def grade(grade_number)
    @db[grade_number] ||= []
  end

  def sort
    sorted = {}
    @db.keys.sort.each do |k|
      sorted[k] = @db[k].sort
    end
    sorted
  end
end
