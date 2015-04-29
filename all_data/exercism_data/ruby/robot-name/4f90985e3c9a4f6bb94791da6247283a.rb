class NameEnumerator

  def initialize
    @enum = Enumerator.new do |enum|
      [*'A'..'Z'].each do |n1|
        [*'A'..'Z'].each do |n2|
          1000.times { |n3| enum.yield "#{n1}#{n2}%03d"%n3 }
        end
      end
      raise "No more Names" 
    end
  end

  def next 
    @enum.next
  end
end

class Robot
  @@name_gen = NameEnumerator.new

  def initialize
    @name = nil
  end

  def name
    @name ||= @@name_gen.next
  end

  def reset
    @name = nil
  end
end
