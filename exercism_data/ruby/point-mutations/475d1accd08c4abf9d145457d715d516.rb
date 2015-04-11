class DNA

  def initialize(original)
    @original_sequence = original
  end

  def hamming_distance(challenger)
    normalizer(challenger)
    difference_counter = 0

    @original_sequence.each_with_index do |nucleotide, i|
      difference_counter = difference_counter + 1 unless challenger[i] == nucleotide
    end

    difference_counter
  end

  private

  def normalizer(challenger)
    @original_sequence = @original_sequence.split('')
    challenger = challenger.split('') 

    @original_sequence = @original_sequence.slice( 0..(challenger.last_index )) if @original_sequence.is_longer?(challenger)
  end
end

Array.class_eval do

  def is_longer?(array)
    length > array.length
  end

  def last_index
    length - 1 
  end
end
