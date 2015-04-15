require 'pry'

class School

  def initialize
    @@school_db = {}
   #@@school_db.default = [] #This construction would not work for me for some reason, so I have the odd methods requiring '[]' later on 
  end

  def db
    @@school_db
  end

  def add(name, grade)
    if not @@school_db[grade]
     @@school_db[grade] = []
    end
    @@school_db[grade] << name 
  end

  def grade(num)
    if @@school_db.key?(num)
      @@school_db.fetch(num)
    else
      []
    end
  end

  def sort
    sorted = {}
    @@school_db.keys.sort.each do |grade|
      sorted[grade] = @@school_db[grade].sort
    end
    return sorted
  end

end
