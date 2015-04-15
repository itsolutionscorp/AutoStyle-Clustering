from samba.dcerpc.nbt import rdata_netbios

__author__ = 'lene'

TRANSCRIPTIONS = { 'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U' }

def transcribe(nucleotide):
    return TRANSCRIPTIONS[nucleotide]

def to_rna(dna):
    rna_list = map(transcribe, dna.upper())
    return ''.join(rna_list)
