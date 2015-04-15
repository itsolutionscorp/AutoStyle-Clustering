class RibonucleicAcid
  attr_reader :code

  def initialize(code)
    @code = code
  end

  def ==(other_object)
    other_object.class == String and return other_object == @code
    super
  end
end

class DeoxyribonucleicAcid
  attr_reader :code

  def initialize(code)
    @code = code
  end

  def to_rna()
    rnaCode = @code.gsub 'T', 'U'
    RibonucleicAcid.new rnaCode
  end

  def ==(other_object)
    other_object.class == String and return other_object == @code
    super
  end
end

class String
  def ==(other_object)
    checkDnaRna other_object and return self == other_object.code
    super
  end

  def checkDnaRna(obj)
    obj.class == RibonucleicAcid || obj.class == DeoxyribonucleicAcid
  end
end
