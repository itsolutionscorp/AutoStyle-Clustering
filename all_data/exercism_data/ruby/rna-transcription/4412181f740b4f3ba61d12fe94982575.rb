# Complement
# This class transcribes RNA and DNA strands
# to their respective complentary strands
class Complement

  # DNA to RNA transcription lookup table
  # Format of the LUT:
  # domain: %w[src_string_with_all_letters mapped_dest_string]
  # Here domain refers to the output of the transctiption
  LUT = {
    RNA: %w[GCTA CGAU],
    DNA: %w[CGAU GCTA]
  }.freeze

  # The transcription scheme is stored as an array and unpacked here as args
  # to String#tr
  def self.of_dna(strand)
    transcribe strand, :RNA
  end

  def self.of_rna(strand)
    transcribe strand, :DNA
  end

  private

  # :nodoc:
  def self.transcribe(str, scheme)
    # Coerce scheme to type Symbol
    scheme = scheme.to_sym
    return nil unless LUT.keys.include? scheme
    return str.tr(*LUT[scheme])
  end

end
