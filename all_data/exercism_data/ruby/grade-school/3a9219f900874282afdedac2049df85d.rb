class School
  attr_reader :db

  def initialize
    @db = {}
  end

  def add(name, grade)
    if !@db[grade]
      @db[grade] = []
    end
    @db[grade].push(name)
  end

  def grade(number)
    if @db[number]
      @db[number]
    else
      []
    end
  end

  def sort
    sorted = {}
    @db.sort.each { |a, b|
      sorted[a] = b
      sorted[a] = sorted[a].sort
    }
    sorted
  end

end
