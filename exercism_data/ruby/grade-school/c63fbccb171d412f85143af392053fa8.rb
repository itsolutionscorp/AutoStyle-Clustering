class School
  attr_accessor :db

  def initialize
    @db = Hash.new { [] }
  end

  def add(person, grade)
    if @db[grade] == []
      @db[grade] = [person]
    else
      @db[grade] << person
    end
  end

  def grade(number)
    @db[number]
  end

  def sort
    d = @db.keys.sort
    deb = {}
    for num in d
      deb[num] = @db[num].sort
    end
    @db = deb
  end
end
