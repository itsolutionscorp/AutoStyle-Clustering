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

private
  def generate_name
    random_letters(2) + random_numbers(3)
  end

  def random_letters(n)
    ('A'..'Z').to_a.sample(n).join("")
  end

  def random_numbers(n)
    min = 10**(n-1)
    max = 10**n
    (rand(max-min)+min).to_s
  end

end
