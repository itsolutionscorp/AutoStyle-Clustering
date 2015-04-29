class Robot
  attr_reader :instruction_count

  def initialize
    @instruction_count = 0
    @last_boot = Time.now
    @created = Time.now
  end

  def name
    increment_instructions
    @name ||= Names.new_name
  end

  def reset
    increment_instructions
    @name = nil
    @last_boot = Time.now
  end

  def timers
    "#{since_last_boot} seconds since last boot, #{since_creation} seconds since creation"
  end

  private

  def increment_instructions
    @instruction_count += 1
  end

  def since_last_boot
    (Time.now - @last_boot).to_i
  end

  def since_creation
    (Time.now - @created).to_i
  end


  class Names

    def self.new_name
      "#{prefix}#{postfix}"
    end

    private

    def self.prefix
      "#{random_letter}#{random_letter}"
    end

    def self.postfix
      "#{random_digit}#{random_digit}#{random_digit}"
    end

    def self.random_digit
      rand 10
    end

    def self.random_letter
      ("A".."Z").to_a[rand(26)]
    end

  end

end
