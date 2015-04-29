class School
  def initialize
    @state = {}
  end

  def add(student, grade)
    # duping rather than modifying in place
    # should prevent inconsistent state
    # if "#to_hash" is called mid-insert
    tmp = @state.dup
    tmp[grade] ||= []
    tmp[grade] = (tmp[grade] << student).uniq.sort
    # ruby hashes maintain insertion sort order, so
    # theoretically this should be cheaper than duping and sorting
    # every time someone calls "#to_hash"
    @state = tmp.keys.sort.reduce({}){|h, key| h[key] = tmp[key];  h }
  end

  def grade(g)
    @state[g] || []
  end

  def to_hash
    @state.dup
  end
end
