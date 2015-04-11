module Hamming

  def self.compute(strand_1, strand_2)
    Computer.new(strand_1, strand_2).mutation_count
  end

  class Computer
    def initialize(strand_1, strand_2)
      @strands = [strand_1, strand_2]
    end

    def length
      @length ||= strands.map(&:length).min
    end

    def sequence(index)
      @sequences ||= []
      @sequences[index] ||= @strands[index][0...length]
    end

    def pairs
      @pairs ||= sequence(0).zip sequences(1)
    end

    def mutations
      @mutations ||= pairs.map { |pair| pair[0] != pair[1] }
    end

    def mutation_count
      @mutation_count ||= pairs.count { |pair| pair[0] != pair[1] }
    end

  end

end
