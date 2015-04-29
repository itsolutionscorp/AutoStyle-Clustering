#!/usr/bin/env ruby -w
#
# @(#) hamming
#
# This script computes the Hamming distance between two given sequences.
#
# [exercism.io reference: http://rosalind.info/problems/hamm/]
#
if ! defined? TEST_SUITE
  TEST_SUITE = "hamming_test.rb"

  class Hamming

    # Return Hamming distance between given sequences, up to the
    # length of the shortest sequence.
    
    def self.compute (seq_a, seq_b)

      len = [seq_a, seq_b].map { |s| s.length }.min
      seq_a.chars[0..len - 1].zip(seq_b.chars[0..len - 1])
        .reduce(0) { |dist_accum, pair| dist_accum + pair.uniq.size - 1 }
    end
  end

  if $0 == __FILE__
    require_relative TEST_SUITE if File.exist? TEST_SUITE
  end
end
