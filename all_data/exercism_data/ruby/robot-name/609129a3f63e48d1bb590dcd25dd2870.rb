require 'set'

class Robot
  def name
    @name ||= unique_name
  end

  def reset
    @name = nil
    name
  end

  private

  @@names_taken = Set.new
  @@prng = Random.new

  def unique_name
    candidate = build_name

    if register_name?(candidate)
      return candidate
    else
      unique_name
    end
  end

  def build_name
    name = ''
    letters = (('a'..'z').to_a + ('A'..'Z').to_a)
    2.times { name += letters[@@prng.rand(52)] }
    3.times { name += @@prng.rand(10).to_s }
    name
  end

  def register_name?(candidate)
    @@names_taken.add?(candidate)
  end
end
