class School
  def initialize
    @school = Hash.new { |hash, key| hash[key] = [] }
  end

  def add name, grade
    @school[grade] << name
  end

  def grade g
    @school[g].sort
  end

  def to_hash
    Hash[@school.each_with_object({}) { |(key, value), hash| hash[key] = value.sort }.sort]
  end

end
