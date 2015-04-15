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
    @school
      .zip
      .sort_by { |a| a[0] }
      .map(&:first)
      .each_with_object({}) { |a, h| h[a.first] = a.last.sort }
  end

end
