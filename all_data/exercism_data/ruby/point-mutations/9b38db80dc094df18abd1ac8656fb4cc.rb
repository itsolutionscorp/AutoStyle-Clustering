class DNA
  def initialize(sequence)
    @sequence = sequence
  end

  def hamming_distance(mutated_sequence)
    count = 0
    if sequence.empty? && mutated_sequence.empty?
      count
    else
      split_sequence.map.with_index do |x, i|
        if split_mutation(mutated_sequence)[i] == nil
          return count
        else
          unless split_mutation(mutated_sequence)[i] == x
            count = count + 1
          end
        end
      end
    end
    count
  end

  private

  attr_reader   :sequence

  def split_sequence
    sequence.chars
  end

  def split_mutation(mutated_sequence)
    mutated_sequence.chars
  end
end
