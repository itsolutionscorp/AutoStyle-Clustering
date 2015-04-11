class School
  attr_reader :db 

  def initialize
    @db = {}
  end

  def add(name, grade)
    @db[grade] ||= []
    @db[grade] << name
  end

  def grade(g)
    @db[g] ||= []
  end

  def sort
    @db = Hash[@db.sort]
    @db.each do |k, v|
      @db[k] = v.sort
    end
  end
end
