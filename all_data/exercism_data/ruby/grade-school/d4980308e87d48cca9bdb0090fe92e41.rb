class School
  attr_accessor :db

  def initialize
    @db = db = {}
  end

  def add(person, grade)
    if @db[grade] == nil
      @db[grade] = [person]
    else
      @db[grade] << person
    end
  end

  def grade(number)
    if @db[number] == nil
      @db[number] = []
    else
      @db[number]
    end	
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
