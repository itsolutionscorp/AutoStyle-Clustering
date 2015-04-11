require 'set'

UsedNames = Set.new

class Robot
  attr_reader :name

  def initialize(used_names = UsedNames)
    @used_names = used_names
    @name = build_name
  end

  def reset
    @name = build_name
  end

  private

  attr_reader :used_names

  def build_name
    name = "#{two_letters}#{three_digits}"

    if used_names.include?(name)
      build_name
    else
      used_names.add(name)
      name
    end
  end

  def two_letters
    2.times.map { letters[rand(26)] }.join
  end

  def three_digits
    3.times.map { rand(10) }.join
  end

  def letters
    @letters ||= ('A'..'Z').to_a
  end
end
