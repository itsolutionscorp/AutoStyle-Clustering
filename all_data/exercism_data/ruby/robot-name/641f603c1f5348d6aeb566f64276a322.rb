class Robot
  attr_reader :instruction_count

  # Used this test file : https://gist.github.com/6101387

  def initialize(now=Time.now)
    @created_at = now
    @instruction_count = 0
  end

  def name
    @instruction_count += 1
    @name ||= generate_name
  end

  def generate_name
    (2.times.map { ('A'..'Z').to_a.shuffle.pop } +
      3.times.map { ('0'..'9').to_a.shuffle.pop }).join('')
  end

  def reset(now=Time.now)
    @last_boot = now
    @name = nil
  end

  def timers(now=Time.now)
    "#{seconds_since_boot(now)} seconds since last boot, " +
      "#{seconds_since_creation(now)} seconds since creation"
  end

  def seconds_since_boot(now=Time.now)
    (now - @last_boot).round
  end

  def seconds_since_creation(now=Time.now)
    (now - @created_at).round
  end
end
