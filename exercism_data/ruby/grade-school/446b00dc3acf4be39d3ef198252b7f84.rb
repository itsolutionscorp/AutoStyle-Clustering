class School
  def initialize
    @db = {}
  end

  def db
    @db.dup
  end

  def add name, grade
    (@db[grade.to_i] ||= []) << name.to_s
  end

  def grade num
    (@db[num] || []).dup
  end

  def sort
    @db.keys.sort.inject({}) { |res, grade| res.merge(grade => @db[grade].sort) } 
  end
end
