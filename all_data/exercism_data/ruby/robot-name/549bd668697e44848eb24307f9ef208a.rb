require 'set'

class Robot

  UNIQUE_COMBINATIONS = (26 * 25 * 10 * 9 * 8).freeze

  @@lock = Mutex.new
  @@history = Set.new

  def name
    @name ||= unique_random_name
  end

  def reset
    @name = nil
  end

private

  def unique_random_name
    while name = random_name
      return name if unique? name
    end
  end

  def unique?(name)
    @@lock.synchronize do
      check_history_size!
      !!@@history.add?(name.hash)
    end
  end

  def check_history_size!
    fail "Unique names exhausted".freeze if @@history.size == UNIQUE_COMBINATIONS
  end

  def random_name
    random_prefix + random_suffix
  end

  def random_prefix
    Array('A'..'Z').sample(2).join
  end

  def random_suffix
    Array(0..9).sample(3).join
  end

end
