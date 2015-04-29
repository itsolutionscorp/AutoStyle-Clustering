#!/usr/bin/env ruby

class Robot
  def name
    @name ||= NameGenerator.new.robot_name
  end

  def reset
    @name = nil
  end
end

class NameGenerator

  def robot_name
    letters(2) + numbers(3)
  end

  private

    DIGITS = (0..9).to_a
    LOWERS = ('a'..'z').to_a
    UPPERS = ('A'..'Z').to_a
    BOTH = (LOWERS+UPPERS)

    def letters(n)
      BOTH.shuffle.take(n).join
    end

    def numbers(n)
      DIGITS.shuffle.take(n).join
    end

end
