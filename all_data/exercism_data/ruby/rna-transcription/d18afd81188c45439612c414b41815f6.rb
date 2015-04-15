class RibonucleicAcid < String
  def initialize(sequence)
    super sequence.to_s
    @sequence = sequence.to_s
  end
end

class DeoxyribonucleicAcid < RibonucleicAcid
  def to_rna
    if @sequence.include?('T')
      @sequence.gsub!('T', 'U')
    end
    RibonucleicAcid.new(@sequence)
  end
end
