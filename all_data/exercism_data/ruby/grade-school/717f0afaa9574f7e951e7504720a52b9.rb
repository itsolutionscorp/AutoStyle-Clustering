class School
  def initialize
    @roster = {}
  end

  def add(student, grade)
    add_grade(grade)
    @roster[grade] << student
  end

  def grade(grade)
    sort_by_grade(grade)
  end

  def to_hash
    sorted_roster = {}

    @roster.sort.map do |key, value|
      sorted_roster[key] = sort_by_grade( key )
    end

    sorted_roster
  end

  private
  def add_grade(grade)
    @roster[grade] = [] unless @roster[grade]
  end

  def sort_by_grade(grade)
    add_grade(grade)
    @roster[grade].sort! unless @roster[grade].nil?
  end
end
