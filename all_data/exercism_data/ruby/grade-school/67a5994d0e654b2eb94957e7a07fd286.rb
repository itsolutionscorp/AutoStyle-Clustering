class School
  attr_accessor :db
  
  def initialize
    @db = Hash.new([])
  end

  def add(name, grade)
    if @db.include?(grade)
      @db[grade] << name
    else
      @db[grade] = [name]
    end
  end

  def grade(grade)
    @db[grade]
  end

  def sort
    @db.keys.sort.inject({}) do |h, k|
      h[k] = @db[k].sort
      h
    end
  end

end
