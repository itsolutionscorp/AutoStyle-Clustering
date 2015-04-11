module Raindrops
  FACTOR_TO_STRING = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

  def self.convert(num)
    s = Subject.new(num)
    if s.factors.empty?
      num.to_s
    else
      s.factors.uniq.map{|e| FACTOR_TO_STRING[e]}.reject{|e| e.nil?}.join
    end
  end


  class Subject
    attr_accessor :num, :working, :factors

    def initialize(num)
      @num = num.to_i
      @working = @num
      @factors = []
      get_factors
    end

    private

    def get_factors
      while n = next_factor
        factors << n
      end
      factors
    end

    def next_factor
      FACTOR_TO_STRING.keys.each do |factor|
        if self.working % factor == 0
          self.working = self.working / factor
          return factor
        end
      end
      nil
    end
  end

end
