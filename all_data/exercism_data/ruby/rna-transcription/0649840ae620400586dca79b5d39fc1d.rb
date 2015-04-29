gem "minitest"
require 'minitest/autorun'

class DNATest < MiniTest::Unit::TestCase
 
  def DNA
    assert_equal 'C', DNA.new("C").to_rna

    assert_equal 'G', DNA.new("G").to_rna
  
    assert_equal 'A', DNA.new("A").to_rna
  
    assert_equal 'U', DNA.new("T").to_rna
  
    assert_equal 'ACGUGGUCUUAA', DNA.new('ACGTGGTCTTAA').to_rna
  end
end
