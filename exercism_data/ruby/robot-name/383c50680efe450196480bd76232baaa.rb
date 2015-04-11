require 'singleton'
require 'pry'

class Robot

  def name
    @name ||= Names.instance.new_name
  end

  def reset
    @name = nil
  end

end

class Names
  include Singleton

  def initialize
    @names = []
    populate_names
  end

  def new_name
    @names.delete_at(rand(@names.length))
  end

  private

  def populate_names
    ('A'..'Z').each do |letter1|
      ('A'..'Z').each do |letter2|
        (0..999).each do |number|
          @names << "#{letter1}#{letter2}#{number.to_s.rjust(3, "0")}"
        end
      end
    end
  end

end

# I feel dirty
