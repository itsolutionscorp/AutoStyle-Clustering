class School
  attr_reader :db

  def initialize
    @db = {}
  end

  def add(name,grade)
    (@db[grade] ||= []).push name
  end

  def grade(grade)
    @db[grade] || []
  end

  def sort()
    ret = {}
    @db.keys.sort.each do |key|
      ret[key] = @db[key].sort
    end
    ret
  end
end
