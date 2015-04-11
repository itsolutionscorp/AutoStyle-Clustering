require 'set'

module Alphabet
  LENGTH = 26
  STARTING_ASCII = 65

  def random_char
    (STARTING_ASCII + rand(LENGTH)).chr
  end
end

class Robot
  include Alphabet

  def self.existing_names
    @existing_names ||= Set.new
  end

  def name
    @name ||= gen_and_store_uniq_name
  end

  def reset
    existing_names.delete name
    @name = nil
  end

  private

  def gen_and_store_uniq_name
    name = gen_uniq_name
    existing_names << name
    name
  end

  def existing_names
    self.class.existing_names
  end

  def gen_uniq_name
    begin
      new_name = gen_name_prefix + gen_name_suffix
    end while existing_names.include?(new_name)
    new_name
  end

  def gen_name_prefix
    random_char + random_char
  end

  def gen_name_suffix
    rand(100..999).to_s
  end
end
