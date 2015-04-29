class School
  attr_reader :db
  def initialize
    @db = Hash.new {|hash, key| hash[key] = [] }
  end

  def add(name, grade)
    @db[grade] << name
  end

  def grade(grade)
    @db[grade]
  end

  def sort
    out = {}
    @db.keys.sort.each do |key|
      out[key] = @db[key].sort
    end

    out
  end
end
